/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.viewfooditem.endpoint;

import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import ryerson.ca.viewfooditem.Business.ViewFoodItemBusiness;
import ryerson.ca.viewfooditem.Helper.FoodItemsXML;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("ViewFoodItem/{query}")
public class ViewFoodItemResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ViewFoodItemResource
     */
    public ViewFoodItemResource() {
    }

    /**
     * Retrieves representation of an instance of ryerson.ca.viewfooditem.endpoint.ViewFoodItemResource
     * @return an instance of java.lang.String
     */
    @GET
     @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String getXml(@PathParam("query") String query) {
        ViewFoodItemBusiness f = new ViewFoodItemBusiness();
        
        String uri = context.getRequestUri().toString();
//        String[] parts = uri.split("\\?");
//        String item = "";
//        if (parts.length > 1) {
//            String queryString = parts[1];
//
//            // Split the query string on the "=" character
//            String[] queryParams = queryString.split("=");
//            if (queryParams.length > 1 && queryParams[0].equals("p")) {
//                item = queryParams[1];
//                System.out.println(item); // prints "pizza"
//            }
//        }
        
        FoodItemsXML books = f.getFoodItemsByQuery(query);
        //System.out.println(">>>>>>>>>>>>>>>>>>" + books);
        
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(FoodItemsXML.class);
        
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
     
StringWriter sw = new StringWriter();
    jaxbMarshaller.marshal(books, sw);
     
    return (sw.toString());
    
      } catch (JAXBException ex) {
            Logger.getLogger(ViewFoodItemResource.class.getName()).log(Level.SEVERE, null, ex);
            return("error happened");
        }
    }

    /**
     * PUT method for updating or creating an instance of ViewFoodItemResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
