package com.klr2003.deprecated.story;

import com.klr2003.deprecated.story.file.StoryCharacterFile;
import com.klr2003.deprecated.story.lang.LanguageMain;
import com.klr2003.console.handlers.ConsoleIOHandler;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

@Deprecated
public class Story extends StoryCharacter {

  private static StringTemplateGroup storyGroup;
  private static StringTemplate storyTemplate;

  private static void readStoryTemplate(String storyName, String chapter) {
    StoryCharacterFile.readCharacterFile();
    storyGroup = new StringTemplateGroup("stories", "stories/" + storyName);
    storyTemplate = storyGroup.getInstanceOf(chapter);
    storyTemplate.setAttribute("first_name", characterFirstName);
    storyTemplate.setAttribute("last_name", characterLastName);
    storyTemplate.setAttribute("full_name", characterFullName);
    storyTemplate.setAttribute("gender", characterGender);
    storyTemplate.setAttribute("eye_colour", characterEyeColour);
    storyTemplate.setAttribute("he_she", LanguageMain.getPersonalSubjectivePronoun());
    storyTemplate.setAttribute("him_her", LanguageMain.getPersonalPossessivePronoun());
    storyTemplate.setAttribute("his_her", LanguageMain.getPersonalObjectivePronoun());
    storyTemplate.setAttribute("young", LanguageMain.getYoung(false));
    storyTemplate.setAttribute("young_plural", LanguageMain.getYoungPlural(false));
    storyTemplate.setAttribute("adult", LanguageMain.getAdult(false));
    storyTemplate.setAttribute("adult_plural", LanguageMain.getAdultPlural(false));
    storyTemplate.setAttribute("self_pronoun", LanguageMain.getSelfPronoun());
  }
  /**
   * Read the story template
   *
   * @param storyName
   * @param chapter
   */
  public static void readStory(String storyName, String chapter) {
    readStoryTemplate(storyName, chapter);
    ConsoleIOHandler.printToConsole(storyTemplate.toString());
  }
}
