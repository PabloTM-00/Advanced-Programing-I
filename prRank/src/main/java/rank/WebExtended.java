package rank;

 
public class WebExtended extends Web {

     @Override
    protected void addSiteWithName(String name) {
        SiteExtended siteEx = new SiteExtended(name);
        addSite(siteEx);
    }

    @Override
    protected void distribute(Site site, double prize) {
        SiteExtended siteEx = (SiteExtended) site;
        if (!siteEx.isValid()) {
             return;
        }
         super.distribute(site, prize);
    }

    public void switchSiteWithName(String name) {
        SiteExtended siteEx = (SiteExtended) getSite(name);
        if (siteEx != null) {
             siteEx.setValid(!siteEx.isValid());
        }
    }
}
