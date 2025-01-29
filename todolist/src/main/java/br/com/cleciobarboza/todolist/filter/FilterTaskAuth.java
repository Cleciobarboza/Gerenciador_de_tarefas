package br.com.cleciobarboza.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.cleciobarboza.todolist.user.IUserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter{

    @Autowired
    private  IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if(servletPath.startsWith("/tasks/")){
            
        
      

     
        //Pegar a autenticação (usuario e senha)
                 var aurthorization= request.getHeader(name: "Authorization");

    
                var authEncoded = Authentication.substring("Basic".length()).trim();

                 byte[] authEncoded = Base64.getDecoder().decode(authEncoded);

                 var authString = new String(authEncoded);
                System.out.println("Authorization");
                System.out.println(authString);
                 //["cleciobarboza "  /   "12345"]
                 String[] credentials = authString.split(":");
                String username = credentials[0];
                  String password = credentials[1];
                 /** 
                 System.out.println("Authorization");
                 System.out.println("username");
                 System.out.println("password");
                 */

                 //Validar usuário
                 var  user =this.userRepository.findByUsername(username);
                      if (user == null){
                             response.sendError(sc:401);
                        }else{
                          //Validar senha
                    var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                        if (passwordVerify.verified){
                            //Segue viagem
                            request.setAttribute(name:"idUser", user.getId());
                            filterChain.doFilter(request, response);

                         }else{
                            response.sendError(sc:401);
             }
                    
            
                } 
            
            }else{

                filterChain.doFilter(request, response);

            }

       


        
        
    
    

    }

    

    
    

    
    
    
    
    
    

}
