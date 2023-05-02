/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.viewmenu.endpoint;

import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import ryerson.ca.viewmenu.Business.ViewMenuBusiness;
import ryerson.ca.viewmenu.Helper.FoodItemsXML;
import ryerson.ca.viewmenu.Helper.Restaurant;
import ryerson.ca.viewmenu.Helper.RestaurantsXML;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("ViewMenu/{query}")
public class ViewMenuResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ViewMenuResource
     */
    public ViewMenuResource() {
    }

    /**
     * Retrieves representation of an instance of ryerson.ca.viewmenu.endpoint.ViewMenuResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String getXml(@PathParam("query") String query) {
        ViewMenuBusiness f = new ViewMenuBusiness();
//        String uri = context.getRequestUri().toString();
//        String[] parts = uri.split("\\?");
//        String item = "";
//        if (parts.length > 1) {
//            String queryString = parts[1];
//
//            // Split the query string on the "=" character
//            String[] queryParams = queryString.split("=");
//            if (queryParams.length > 1 && queryParams[0].equals("p")) {
//                item = queryParams[1];
//                item = item.replace("%20", " "); // replace %20 with space
//                item = item.replace("%27", "'"); // replace %27 with '
//                System.out.println(item); // prints "pizza" if the query parameter is "p=pizza"
//            }
//        }
        
        //RestaurantsXML books = f.getRestaurantsByQuery(item);
        System.out.println("AT VIEWMENURESOURCE CLASS: QUERY IS=" + query);
        RestaurantsXML rest = f.getRestaurantsByQuery(query);
        //System.out.println(">>>>>>>>>>>>>>>>>>" + books);
        
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(RestaurantsXML.class);
        
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
     
StringWriter sw = new StringWriter();
    //jaxbMarshaller.marshal(books, sw);
    jaxbMarshaller.marshal(rest, sw);
     
    return (sw.toString());
    
      } catch (JAXBException ex) {
            Logger.getLogger(ViewMenuResource.class.getName()).log(Level.SEVERE, null, ex);
            return("error happened");
        }
    }

    /**
     * PUT method for updating or creating an instance of ViewMenuResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
