/*
 *    Geotoolkit.org - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2018, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotoolkit.coverage.sql;

import java.util.List;
import java.util.ArrayList;
import java.nio.file.Path;
import org.opengis.referencing.operation.TransformException;
import org.apache.sis.storage.Aggregate;
import org.apache.sis.storage.DataStore;
import org.apache.sis.storage.DataStoreException;
import org.apache.sis.storage.DataStores;
import org.apache.sis.storage.GridCoverageResource;
import org.apache.sis.storage.Resource;
import org.apache.sis.coverage.SampleDimension;
import org.apache.sis.coverage.grid.GridGeometry;
import org.geotoolkit.coverage.io.GridCoverageReader;
import org.geotoolkit.internal.coverage.CoverageUtilities;


/**
 * Information about a new raster to be added.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class NewRaster {
    /**
     * The format name as given by the data store provider. Example: "NetCDF".
     */
    final String driver;

    /**
     * Path to the new file to add.
     */
    final Path path;

    /**
     * 0-based index of the image in the file.
     */
    final int imageIndex;

    /**
     * Grid geometry of the file to add.
     */
    GridGeometry geometry;

    /**
     * Description of bands.
     */
    List<SampleDimension> bands;

    /**
     * Creates information about a new raster to be added to the catalog.
     */
    NewRaster(final String driver, final Path file, final int index) {
        this.driver = driver;
        this.path   = file;
        imageIndex  = index;
    }

    /**
     * Returns a string representation of this raster entry for debugging purpose.
     *
     * @return string representation (may change in any future version).
     */
    @Override
    public String toString() {
        return path.getFileName().toString() + " @ " + imageIndex;
    }

    static List<NewRaster> list(final Path... files) throws DataStoreException {
        final List<NewRaster> rasters = new ArrayList<>(files.length);
        for (final Path file : files) {
            try (final DataStore ds = DataStores.open(file)) {
                String driver = ds.getProvider().getShortName();
                collect(driver, file, ds, rasters, 0);
            } catch (TransformException e) {
                throw new CatalogException(e);
            }
        }
        return rasters;
    }

    /**
     * Adds to the given {@code rasters} list information about all resources found in the given node.
     * This method add recursively all children resources.
     *
     * @param  driver    the format name as given by the data store provider. Example: "NetCDF".
     * @param  file      path to the file, including parent directories and file extension.
     * @param  resource  the resource to add to the given {@code rasters} list.
     * @param  rasters   where to add the information about rasters.
     * @param  index     zero-based index of the image to read.
     */
    private static void collect(final String driver, final Path file, final Resource resource,
            final List<NewRaster> rasters, int index) throws DataStoreException, TransformException
    {
        if (resource instanceof Aggregate) {
            for (final Resource child : ((Aggregate) resource).components()) {
                collect(driver, file, child, rasters, index++);
            }
        } else if (resource instanceof GridCoverageResource) {
            final NewRaster r = new NewRaster(driver, file, index);
            final GridCoverageResource gr = (GridCoverageResource) resource;
            r.geometry = gr.getGridGeometry();
            r.bands = gr.getSampleDimensions();
            rasters.add(r);
        } else if (resource instanceof org.geotoolkit.storage.coverage.GridCoverageResource) {
            final NewRaster r = new NewRaster(driver, file, index);
            GridCoverageReader reader = ((org.geotoolkit.storage.coverage.GridCoverageResource) resource).acquireReader();
            r.geometry = CoverageUtilities.toSIS(reader.getGridGeometry(r.imageIndex));
            r.bands = CoverageUtilities.toSIS(reader.getSampleDimensions(r.imageIndex));
            reader.dispose();
            rasters.add(r);
        }
    }
}
