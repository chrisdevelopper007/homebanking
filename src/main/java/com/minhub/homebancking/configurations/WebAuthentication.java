package com.minhub.homebancking.configurations;

import com.minhub.homebancking.Repository.ClientRepository;
import com.minhub.homebancking.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration //anotacion que indica a spring que debe crear un objeto de este tipo cuando se este iniciando la app, para que cuando se configure el modulo de srping
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired // inyeccion de dependencia que dice que en mi clase webconfiguration quiero generar una instancia de mi repositorio para usarlo y acceder a ello
    ClientRepository clientRepository;



    @Override // voy a sobre escribir el metodo init que viene con la herencia GlobalAuthenticationConfigurerAdapter

    public void init(AuthenticationManagerBuilder auth) throws Exception {  //AuthenticationManagerBuilder= alguien que se encarga de manejar las autenticaciones y me las construye) // throws Exception coloca las siguientes excepciones

        auth.userDetailsService(inputEmail-> {

            Client client = clientRepository.findByEmail(inputEmail); //deja entrar a las personas que encuentres por email en el repositorio

            if (client != null) {
                if (client.getEmail().equals("christian@mindhub.com")) {
                    return new User(client.getEmail(), client.getPassword(),
                            AuthorityUtils.createAuthorityList("ADMIN")); //

                }
                else {
                    return new User(client.getEmail(), client.getPassword(), //

                            AuthorityUtils.createAuthorityList("CLIENT")); //
                }



            } else {

                throw new UsernameNotFoundException("Unknown client: " + inputEmail);


            }

        });

    }

    @Bean //La anotación @Bean genera un objeto de tipo PasswordEncoder en el ApplicationContext para que luego se pueda usar en cualquier parte de la aplicación que se requiera.

    public PasswordEncoder passwordEncoder() {

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }

}

