package org.acme.rest;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.agroal.api.AgroalDataSource;

@Path("/peoples")
public class Registration {

    @Inject
    AgroalDataSource defaultDataSource;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Set<People> hello() throws SQLException {
        Connection connection = defaultDataSource.getConnection();
        Statement stm = connection.createStatement();

        ResultSet result = stm.executeQuery("SELECT * FROM peoples");

        Set<People> persons = Collections.newSetFromMap(
                Collections.synchronizedMap(new LinkedHashMap<>()));

        while (result.next()) {
            persons.add(new People(result.getString("name"), result.getString("age")));
        }

        return persons;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{name}/{age}")
    public void save(@PathParam String name, @PathParam int age) throws SQLException {
        Connection connection = defaultDataSource.getConnection();
        Statement stm = connection.createStatement();

        stm.execute("INSERT INTO peoples (name, age) VALUES ('" + name + "'," + age + ")");
    }
}
