/*
 *    Geotoolkit.org - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2010, Open Source Geospatial Foundation (OSGeo)
 *    (C) 2010, Geomatys
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
package org.geotoolkit.coverage.io;

import java.util.Locale;
import java.util.concurrent.CancellationException;

import org.geotoolkit.resources.Errors;
import org.geotoolkit.util.Localized;


/**
 * Base class of {@link GridCoverageReader} and {@link GridCoverageWriter}.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @version 3.12
 *
 * @since 3.12
 * @module
 */
public abstract class GridCoverageStore implements Localized {
    /**
     * The locale to use for formatting messages, or {@code null} for a default locale.
     */
    Locale locale;

    /**
     * {@code true} if a request to abort the current read or write operation has been made.
     * Subclasses should set this field to {@code false} at the begining of each read or write
     * operation, and pool the value regularly during the operation.
     *
     * @see #abort()
     */
    protected volatile boolean abortRequested;

    /**
     * Creates a new instance.
     */
    protected GridCoverageStore() {
    }

    /**
     * Returns the locale to use for formatting warnings and error messages,
     * or {@code null} for the {@linkplain Locale#getDefault() default}.
     *
     * @return The current locale, or {@code null}.
     *
     * @see javax.imageio.ImageReader#getLocale()
     * @see javax.imageio.ImageWriter#getLocale()
     */
    @Override
    public Locale getLocale() {
        return locale;
    }

    /**
     * Sets the current locale of this coverage reader or writer to the given value. A value of
     * {@code null} removes any previous setting, and indicates that the reader or writer should
     * localize as it sees fit.
     *
     * @param locale The new locale to use.
     *
     * @see javax.imageio.ImageReader#setLocale(Locale)
     * @see javax.imageio.ImageWriter#setLocale(Locale)
     */
    public void setLocale(final Locale locale) {
        this.locale = locale;
    }

    /**
     * Returns a localized string for the specified error key.
     *
     * @param key One of the constants declared in the {@link Errors.Keys} inner class.
     */
    final String formatErrorMessage(final int key) {
        return Errors.getResources(locale).getString(key);
    }

    /**
     * Cancels the read or write operation which is currently under progress in an other thread.
     * The operation will throw a {@link CancellationException}, unless it had the time to complete.
     *
     * {@section Note for implementors}
     * Subclasses should set the {@link #abortRequested} field to {@code false} at the beginning
     * of each read or write operation, and poll the value regularly during the operation.
     *
     * @see #abortRequested
     * @see javax.imageio.ImageReader#abort()
     * @see javax.imageio.ImageWriter#abort()
     */
    public void abort() {
        abortRequested = true;
    }

    /**
     * Throws {@link CancellationException} if a request to abort the current read or write
     * operation has been made since this object was instantiated or {@link #abortRequested}
     * has been cleared.
     *
     * @throws CancellationException If the {@link #abort()} method has been invoked.
     */
    final void checkAbortState() throws CancellationException {
        if (abortRequested) {
            throw new CancellationException(formatErrorMessage(Errors.Keys.CANCELED_OPERATION));
        }
    }

    /**
     * Restores this reader or writer to its initial state.
     *
     * @throws CoverageStoreException if an error occurs while restoring to the initial state.
     *
     * @see javax.imageio.ImageReader#reset()
     * @see javax.imageio.ImageWriter#reset()
     */
    public void reset() throws CoverageStoreException {
        locale = null;
        abortRequested = false;
    }

    /**
     * Allows any resources held by this reader or writer to be released. The result of calling
     * any other method subsequent to a call to this method is undefined.
     * <p>
     * The default implementation invokes {@link #reset()}. Subclass implementations
     * should ensure that all resources, especially JCBC connections, are released.
     *
     * @throws CoverageStoreException if an error occurs while disposing resources.
     *
     * @see javax.imageio.ImageReader#dispose()
     * @see javax.imageio.ImageWriter#dispose()
     */
    public void dispose() throws CoverageStoreException {
        reset();
    }
}
