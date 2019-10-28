package jhou.tablecodes;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link AssetStatus}.
 *
 * @author Developers
 *
 */
public interface IAssetStatus extends IEntityDao<AssetStatus> {

    static final IFetchProvider<AssetStatus> FETCH_PROVIDER = EntityUtils.fetch(AssetStatus.class).with(
        // TODO: uncomment the following line and specify the properties, which are required for the UI. Then remove the line after.
        // "key", "desc");
        "Please specify the properties, which are required for the UI");

}
