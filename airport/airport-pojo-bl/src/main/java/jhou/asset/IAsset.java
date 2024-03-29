package jhou.asset;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Asset}.
 *
 * @author Developers
 *
 */
public interface IAsset extends IEntityDao<Asset> {

    static final IFetchProvider<Asset> FETCH_PROVIDER = EntityUtils.fetch(Asset.class).with(
        "key", "desc", "status", "parent", "peer");

}
