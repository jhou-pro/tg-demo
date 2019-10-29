package jhou.asset;

import ua.com.fielden.platform.dao.IEntityDao;
import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;

/**
 * Companion object for entity {@link AssetCertification}.
 *
 * @author Developers
 *
 */
public interface IAssetCertification extends IEntityDao<AssetCertification> {

    static final IFetchProvider<AssetCertification> FETCH_PROVIDER = EntityUtils.fetch(AssetCertification.class).with(
        "asset", "certification", "desc");

}
