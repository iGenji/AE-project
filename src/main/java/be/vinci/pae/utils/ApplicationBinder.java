package be.vinci.pae.utils;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import be.vinci.pae.domain.AddressDAO;
import be.vinci.pae.domain.AddressDAOImpl;
import be.vinci.pae.domain.AddressFactory;
import be.vinci.pae.domain.AddressFactoryImpl;
import be.vinci.pae.domain.FurnitureDAO;
import be.vinci.pae.domain.FurnitureDAOImpl;
import be.vinci.pae.domain.FurnitureFactory;
import be.vinci.pae.domain.FurnitureFactoryImpl;
import be.vinci.pae.domain.PhotoFactory;
import be.vinci.pae.domain.PhotoFactoryImpl;
import be.vinci.pae.domain.UserDAO;
import be.vinci.pae.domain.UserDAOImpl;
import be.vinci.pae.domain.UserFactory;
import be.vinci.pae.domain.UserFactoryImpl;
import be.vinci.pae.services.DalServices;
import be.vinci.pae.services.DalServicesImpl;
import be.vinci.pae.services.DalTransactions;
import be.vinci.pae.useCases.FurnitureUCC;
import be.vinci.pae.useCases.FurnitureUCCImpl;
import be.vinci.pae.useCases.UserUCC;
import be.vinci.pae.useCases.UserUCCImpl;
import jakarta.inject.Singleton;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ApplicationBinder extends AbstractBinder {

  @Override
  protected void configure() {
    bind(UserFactoryImpl.class).to(UserFactory.class).in(Singleton.class);
    bind(UserUCCImpl.class).to(UserUCC.class).in(Singleton.class);
    bind(UserDAOImpl.class).to(UserDAO.class).in(Singleton.class);
    bind(AddressFactoryImpl.class).to(AddressFactory.class).in(Singleton.class);
    bind(FurnitureFactoryImpl.class).to(FurnitureFactory.class).in(Singleton.class);
    bind(FurnitureUCCImpl.class).to(FurnitureUCC.class).in(Singleton.class);
    bind(FurnitureDAOImpl.class).to(FurnitureDAO.class).in(Singleton.class);
    bind(PhotoFactoryImpl.class).to(PhotoFactory.class).in(Singleton.class);
    bind(DalServicesImpl.class).to(DalTransactions.class).to(DalServices.class).in(Singleton.class);
    bind(AddressDAOImpl.class).to(AddressDAO.class).in(Singleton.class);



  }
}
