import rank.*;

import java.util.Set;

public class MainRank {
    public static void main(String[] args) {
        String[] links = {"I->C",
			"J->C",
			"A->C",
			"A->D",
			"B->C",
			"B->F",
			"D->F",
			"E->B",
			"E->H",
			"F->G",
			"F->H",
			"G->E",
			"G->H"};

        Web web = new Web();
        for (String arc: links){
            web.addLink(arc);
		  }
        System.out.println(web);
        web.simulateClick(4000);
        System.out.println("Pages sorted alphabetically");
        System.out.println(web.getSitesByName());
        System.out.println("Pages sorted by rank");
        System.out.println(web.getSitesByRank());

        System.out.println();

        WebExtended webe = new WebExtended();
        for (String arc: links)
            webe.addLink(arc);
        webe.switchSiteWithName("A");
        webe.switchSiteWithName("I");
        webe.switchSiteWithName("J");
        System.out.println(webe);
        webe.simulateClick(4000);
        System.out.println("Pages sorted alphabetically");
        System.out.println(webe.getSitesByName());
        System.out.println("Pages sorted by rank");
        System.out.println(webe.getSitesByRank());
    }
}
