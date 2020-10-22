package com.klr2003.deprecated.story.lang;

@Deprecated
public class LanguageMain {

  private static String personalSubjectivePronoun;
  private static String personalPossessivePronoun;
  private static String personalObjectivePronoun;
  private static String selfPronoun;

  // Change this name. Basically, this represents the main character as a boy or a girl.
  private static String young;
  // For expressions like, We are Boys or Girls Rule
  private static String youngPlural;
  // Change this also. this represents the main character as a man or a woman.
  private static String adult;
  private static String adultPlural;

  public static void setGenderValues(String gender) {
    if (gender.matches("male")) {
      personalSubjectivePronoun = "He";
      personalObjectivePronoun = "Him";
      personalPossessivePronoun = "His";
      selfPronoun = "Himself";
      young = "boy";
      youngPlural = "boys";
      adult = "man";
      adultPlural = "men";
    } else {
      personalSubjectivePronoun = "She";
      // I didn't include the Her for the possessive pronoun as it has a objective equivalent.
      personalPossessivePronoun = "Hers";
      personalObjectivePronoun = "Her";
      selfPronoun = "Herself";
      young = "girl";
      youngPlural = "girls";
      adult = "woman";
      adultPlural = "women";
    }
  }

  public static String getPersonalSubjectivePronoun() {
    return personalObjectivePronoun;
  }

  public static String getPersonalPossessivePronoun() {
    return personalPossessivePronoun;
  }

  public static String getPersonalObjectivePronoun() {
    return personalObjectivePronoun;
  }

  public static String getSelfPronoun() {
    return selfPronoun;
  }

  public static String getYoung(boolean isCapital) {
    if (isCapital) return young.substring(0, 1).toUpperCase() + young.substring(1);
    else return young;
  }

  public static String getYoungPlural(boolean isCapital) {
    if (isCapital) return youngPlural.substring(0, 1).toUpperCase() + youngPlural.substring(1);
    else return youngPlural;
  }

  public static String getAdult(boolean isCapital) {
    if (isCapital) return adult.substring(0, 1).toUpperCase() + adult.substring(1);
    else return adult;
  }

  public static String getAdultPlural(boolean isCapital) {
    if (isCapital) return adultPlural.substring(0, 1).toUpperCase() + adultPlural.substring(1);
    else return adultPlural;
  }
}
