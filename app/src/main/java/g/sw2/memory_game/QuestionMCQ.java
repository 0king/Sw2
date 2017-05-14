package g.sw2.memory_game;

import java.util.List;

/**
 * Created by 5dr on 15/04/17.
 */

public class QuestionMCQ {
	
	/* A model for a MCQ question (also called a query, an item, a case) */
	
	String questionText;
	List<String> multipleOptions;//usually varies between 2-5
	String[] allPossibleOptions; //contains many options so that different options can be provided every time; b/w 0-50
	String correctAnswer;
	String questionExplainedSolution;
	
	/* Other properties: */
	
	String questionMediaUrl;
	String questionPositiveScore;
	String questionNegativeScore;
	
	public int getTotalNoOfOptions() {
		return multipleOptions.size();
	}
	
	public String getQuestionText() {
		return questionText;
	}
	
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	
	public List<String> getMultipleOptions() {
		return multipleOptions;
	}
	
	public void setMultipleOptions(List<String> multipleOptions) {
		this.multipleOptions = multipleOptions;
	}
	
	public String[] getAllPossibleOptions() {
		return allPossibleOptions;
	}
	
	public void setAllPossibleOptions(String[] allPossibleOptions) {
		this.allPossibleOptions = allPossibleOptions;
	}
	
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	public String getQuestionExplainedSolution() {
		return questionExplainedSolution;
	}
	
	public void setQuestionExplainedSolution(String questionExplainedSolution) {
		this.questionExplainedSolution = questionExplainedSolution;
	}
	
	public String getQuestionMediaUrl() {
		return questionMediaUrl;
	}
	
	public void setQuestionMediaUrl(String questionMediaUrl) {
		this.questionMediaUrl = questionMediaUrl;
	}
	
	public String getQuestionPositiveScore() {
		return questionPositiveScore;
	}
	
	public void setQuestionPositiveScore(String questionPositiveScore) {
		this.questionPositiveScore = questionPositiveScore;
	}
	
	public String getQuestionNegativeScore() {
		return questionNegativeScore;
	}
	
	public void setQuestionNegativeScore(String questionNegativeScore) {
		this.questionNegativeScore = questionNegativeScore;
	}
}
