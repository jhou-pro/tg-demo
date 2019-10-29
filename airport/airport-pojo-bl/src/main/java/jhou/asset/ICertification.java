package jhou.asset;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Certification}.
 *
 * @author Developers
 *
 */
public interface ICertification extends IEntityDao<Certification> {

    static final IFetchProvider<Certification> FETCH_PROVIDER = EntityUtils.fetch(Certification.class).with(
        // TODO: uncomment the following line and specify the properties, which are required for the UI. Then remove the line after.
        // "key", "desc");
        "Please specify the properties, which are required for the UI");

}
