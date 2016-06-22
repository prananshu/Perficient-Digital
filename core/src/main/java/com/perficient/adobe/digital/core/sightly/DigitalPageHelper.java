package com.perficient.adobe.digital.core.sightly;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;

public class DigitalPageHelper extends WCMUse {

	/** The current page. */
    private Page currentPage;
  /*
   * (non-Javadoc)
   *
   * @see com.adobe.cq.sightly.WCMUsePojo#activate()
   */
  @Override
  public void activate() throws Exception {
      currentPage = getCurrentPage();
  }
    
    /**
     * Gets the page title.
     *
     * @return the page title
     */
    public String getPageTitle() {
        String pageTitle = currentPage.getName();
        if (StringUtils.isNotEmpty(currentPage.getTitle())) {
            pageTitle = currentPage.getTitle();
        }
        if (StringUtils.isNotEmpty(currentPage.getPageTitle())) {
            pageTitle = currentPage.getPageTitle();
        }
        return pageTitle;
    }
    
    /**
    * Gets the description.
    *
    * @return the description
    */
    public String getDescription() {
      return getPageProperties().get("jcr:description", StringUtils.EMPTY);
    }
    
    /**
     * Gets the trainer name.
     *
     * @return the trainer name
     */
     public String getTrainer() {
       return getPageProperties().get("jcr:trainer", StringUtils.EMPTY);
     }
    
     /**
      * Gets the training date.
      *
      * @return the training date
      */
      public String getTrainingDate() {
        return getPageProperties().get("jcr:date", StringUtils.EMPTY);
      }
      
      /**
       * Gets the author name.
       *
       * @return the author name
       */
       public String getAuthor() {
         return getPageProperties().get("jcr:author", StringUtils.EMPTY);
       }
      
    /**
     * Gets the keywords.
     *
     * @return the keywords
     */
    public String getKeywords() {

        StringBuilder tags = new StringBuilder();
        Tag[] tagArray = currentPage.getTags();

        if (tagArray != null && tagArray.length > 0) {
            for (Tag tag : tagArray) {
                if (tag != null) {
                    tags.append(tag.getTitle());
                    tags.append(", ");
                }
            }
        }

        return StringUtils.removeEnd(tags.toString(), ", ");
    }
    
    /**
    * Gets the page language.
    *
    * @return the page language
    */
    public String getPageLanguage() {
      String pageLanguage = "en";
      Locale locale = currentPage.getLanguage(true);
      if (locale != null) {
        pageLanguage = locale.getLanguage();
      }
      return pageLanguage;
    }

}