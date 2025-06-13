package rank;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Web {
	
	protected Set<Site> sites;
	private Set<Link> links;
	private static final double THRESHOLD = 1E-5;
	
	public Web() {
		sites = new HashSet <>();
		links = new HashSet <>();
	}

	protected void addSite(Site site) {
		sites.add(site);
	}
	
	protected void addSiteWithName(String name) {
		Site site = new Site(name);
		
		addSite(site);
	}
	
	public void addLink(String dataLink) throws IllegalArgumentException  {
		if(!dataLink.contains("->")) {
			throw new IllegalArgumentException("Invalid format: " + dataLink);
		}
		
		String[] parts = dataLink.split("->");
		
		String origin = parts[0].trim();
		String link = parts[1].trim();
		
		addSiteWithName(origin);
		addSiteWithName(link);
		
		links.add(new Link(origin,link));
	}
	
	
	
	public Site getSite(String name) throws NoSuchElementException {
		for (Site site : sites) {
	        if (site.getName().equalsIgnoreCase(name)) {
	            return site;
	        }
	    }
	    throw new NoSuchElementException("Non-existent page: " + name);
	}
	
	
	public Set<String> getNames() {
	    Set<String> names = new HashSet<>();
	    
	    for (Site site : sites) {
	        names.add(site.getName());
	    }
	    return names;
	}

	private Set<Site> getSitesLinkedFrom(Site page){
		Set<Site> linkedSites = new HashSet<>( );
		
		for(Link link : links) {
			if(link.getOrigin().equalsIgnoreCase(page.getName())) {
				Site dest = getSite(link.getLinked());
				linkedSites.add(dest);
			}
		}
		return linkedSites;
		
	}
	
	protected void distribute(Site site, double prize) {
		if(prize > THRESHOLD) {
			site.addRank(prize/2);
			
			 Set<Site> linkedSites = getSitesLinkedFrom(site);
			 
			 if(!linkedSites.isEmpty()) {
				 int n = linkedSites.size();
				 double sharedPrize = prize/(2*n);
			 
				 for(Site linked : linkedSites) {
					 distribute(linked,sharedPrize);
				 }
			 }
		}
	}
	
	public void click(String name) {
		try {
			Site site = getSite(name);
			distribute(site,1);
		}
		catch(NoSuchElementException e) {
			// Ignore
		}
	}
	
	public void simulateClick(int numClick) {
		Random rndm = new Random(1);
			
		List<String> pages = new ArrayList <> (getNames());
		
		if(pages.isEmpty()) {
			return;
		}
		
		for(int i = 0; i < numClick;i++) {
			int pos = rndm.nextInt(pages.size());
			String name = pages.get(pos);
			click(name);
		}
	}
	
	public SortedSet<Site> getSitesByName(){
		return new TreeSet<>(sites);
	}
	
	public SortedSet<Site> getSitesByRank() {
	    Comparator<Site> cmp = (a, b) -> {
	        int result = Double.compare(b.getRank(), a.getRank()); 
	        if (result == 0) {
	            result = a.getName().compareToIgnoreCase(b.getName()); 
	        }
	        return result;
	    };

	    SortedSet<Site> sorted = new TreeSet<>(cmp);
	    
	    sorted.addAll(sites);
	    return sorted;
	}


	@Override
	public String toString() {
		return "Web(" +"[" +  sites+ "]" + "[" + links + "] \n"
				+ "Pages sorted alphabetically \n [" + getSitesByName() + "]" +
				"\n Pages sorted by rank \n [ " + getSitesByRank() + "]";
	}
	
	
	
	
}
