/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2012, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 2.1 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotoolkit.gml.xml;

import java.util.List;
import java.util.stream.DoubleStream;

/**
 * TODO : optimize by adding a method to return coordinate values as {@link DoubleStream}.
 * It will avoid unnecessary value boxing and accumulation.
 *
 * @author Guilhem Legal (Geomatys)
 */
public interface Coordinates {

    String getValue();

    List<Double> getValues();

    String getCs();

    String getTs();

    String getDecimal();
}
