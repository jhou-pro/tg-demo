package jhou.asset;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link AssetCertification}.
 *
 * @author Developers
 *
 */
public interface IAssetCertification extends IEntityDao<AssetCertification> {

    static final IFetchProvider<AssetCertification> FETCH_PROVIDER = EntityUtils.fetch(AssetCertification.class).with(
        // TODO: uncomment the following line and specify the properties, which are required for the UI. Then remove the line after.
        // "key", "desc");
        "Please specify the properties, which are required for the UI");

}