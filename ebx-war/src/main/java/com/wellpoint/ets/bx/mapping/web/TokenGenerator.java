/*
 * Created on Jul 13, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.web;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * @author u22093
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TokenGenerator {

private static TokenGenerator instance = new TokenGenerator();

 public static TokenGenerator getInstance() {
 	return instance;
 }

 protected TokenGenerator() {
         super();
    }

 private long previous;

 public synchronized boolean isTokenValid(HttpServletRequest request) {
 		return this.isTokenValid(request, false);
  }
	
 public synchronized boolean isTokenValid(HttpServletRequest request,
 		boolean reset) {

  HttpSession session = request.getSession(false);
  if (session == null) {
    return false;
   }

  String saved = (String) session.getAttribute("TRANSACTION_TOKEN_KEY");
  if (saved == null) {
         return false;
    }
    if (reset) {
       this.resetToken(request);
    }
     String token = request.getParameter("tokenKey");
	 if (token == null) {
	        return false;
	    }
       return saved.equals(token);
     }

  public synchronized void resetToken(HttpServletRequest request) {

       HttpSession session = request.getSession(false);
       if (session == null) {
          return;
        }
       session.removeAttribute("TRANSACTION_TOKEN_KEY");
    }

 public synchronized void saveToken(HttpServletRequest request) {

   HttpSession session = request.getSession();
   String token = generateToken(request);
     if (token != null) {
          session.setAttribute("TRANSACTION_TOKEN_KEY", token);
      }
   }

  public synchronized String generateToken(HttpServletRequest request) {
      HttpSession session = request.getSession();
     try {
           byte id[] = session.getId().getBytes();
           long current = System.currentTimeMillis();
            if (current == previous) {
            current++;
          }
          previous = current;
           byte now[] = Long.valueOf(current).toString().getBytes();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(id);
           md.update(now);
            return toHex(md.digest());
       } catch (NoSuchAlgorithmException e) {
            return null;
       }

    }
  private String toHex(byte buffer[]) {
       StringBuffer sb = new StringBuffer(buffer.length * 2);
        for (int i = 0; i < buffer.length; i++) {
            sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
             sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
         }
         return sb.toString();
   }

 }
