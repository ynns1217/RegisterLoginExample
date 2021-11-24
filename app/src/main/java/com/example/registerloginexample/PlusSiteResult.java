package com.example.registerloginexample;

   public class PlusSiteResult {
      String plusSite;
      String plusID;

      public PlusSiteResult(String plusSite, String plusID, String plusPassword) {
         this.plusSite = plusSite;
         this.plusID = plusID;
         this.plusPassword = plusPassword;
      }

      String plusPassword;

      public String getPlusSite() {
            return plusSite;
         }

      public void setPlusSite(String plusSite) {
         this.plusSite = plusSite;
      }

      public String getPlusID() {
         return plusID;
      }

      public void setPlusID(String plusID) {
         this.plusID = plusID;
      }

      public String getPlusPassword() {
         return plusPassword;
      }

      public void setPlusPassword(String plusPassword) {
         this.plusPassword = plusPassword;
      }

   }