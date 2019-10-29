package jhou.asset.exceptions;

/**
 * An exception to indicate asset-related exceptions.
 *
 * @author Developers
 *
 */
public class AssetModuleException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AssetModuleException(final String msg) {
        super(msg);
    }

    public AssetModuleException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

}
