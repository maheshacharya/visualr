package org.macharya.targeting;


import com.onehippo.cms7.targeting.collectors.AbstractCollector;
import com.onehippo.cms7.targeting.geo.GeoIPService;
import org.hippoecm.hst.util.HstRequestUtils;
import org.macharya.services.StateLocation;
import org.macharya.services.StateServiceImpl;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by maheshacharya on 8/4/16.
 */
public class GeoIPStateProvinceCollector extends AbstractCollector<StateProvinceTargetingData,
        StateProvinceRequestData> {
    public static final String ID = "geoStateProvince";
    private final GeoIPService geoIPService;

    public GeoIPStateProvinceCollector(String id, Node node) throws RepositoryException {
        super(id);
        this.assertEqualIds(ID, id, GeoIPStateProvinceCollector.class.getSimpleName(), node);
        //this.geoIPService = (GeoIPService) HstServices.getComponentManager().getComponent(GeoIPService.class.getName(), new String[]{"com.onehippo.cms7.targeting"});
        geoIPService = new StateServiceImpl();
    }

    GeoIPStateProvinceCollector(GeoIPService geoIPService) {
        super(ID);
        this.geoIPService = geoIPService;
    }

    public StateProvinceRequestData getTargetingRequestData(HttpServletRequest request,
                                                            boolean newVisitor,
                                                            boolean newVisit,
                                                            StateProvinceTargetingData previousTargetingData) {
        if (!newVisit) {
            if (previousTargetingData == null) {
                return null;
            } else {
                StateLocation loc = new StateLocation("", "", "", 0, 0);
                loc.setState(previousTargetingData.getState());
                return new StateProvinceRequestData(loc);
            }
        } else if (this.geoIPService != null) {
            String ip = HstRequestUtils.getFarthestRemoteAddr(request);
            StateLocation location = (StateLocation) this.geoIPService.getLocation(ip);
            return new StateProvinceRequestData(location);//"MA");
        } else {
            return null;
        }
    }

    /**
     * @param targetingData
     * @param requestData
     * @return
     * @throws IllegalArgumentException
     */
    public StateProvinceTargetingData updateTargetingData(StateProvinceTargetingData targetingData,
                                                          StateProvinceRequestData requestData)
            throws IllegalArgumentException {
        if (targetingData == null) {
            targetingData = new StateProvinceTargetingData(this.getId());
        }
        targetingData.setState(requestData.getState());
        return targetingData;
    }
}