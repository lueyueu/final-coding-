package rocket.app.view;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import eNums.eAction;
import exceptions.RateException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController implements Initializable{

	private MainApp mainApp;
	
	//	TODO - RocketClient.RocketMainController
	public MortgageController(){
	}
	//	Create private instance variables for:
	
	
	
	
//	TextBox  - 	txtIncome
//	TextBox  - 	txtExpenses
//	TextBox  - 	txtCreditScore
//	TextBox  - 	txtHouseCost
	@FXML
	private Button btnPayment;
	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpenses;
	@FXML
	private TextField txtCreditScore;
	@FXML
	private TextField txtHouseCost;
	@FXML
	private TextField txtDownPayment;
	@FXML
	private ComboBox loanTerm;
	
	
	
	

//		ComboBox -	loan term... 15 year or 30 year
//		Labels   -  various labels for the controls
//		Button   -  button to calculate the loan payment
	@FXML
	private Label LIncome;
	@FXML
	private Label Iexpenses;
	@FXML
	private Label ICreditScore;
	@FXML
	private Label IHouseCost;
	@FXML
	private Label ITerm;
	@FXML
	private Label IDownPayment;
	
	@FXML
	public Label lblError;
	
	
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	//	TODO - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	
	
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		
		
		
		
		Action f = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		
		lq.setdIncome(Double.parseDouble(txtIncome.getText()));
		lq.setdExpenses(Double.parseDouble(txtExpenses.getText()));
		lq.setdAmount(Double.parseDouble(txtHouseCost.getText())-Double.parseDouble(txtDownPayment.getText()));
		if(loanTerm.getSelectionModel().getSelectedItem().toString() == "15 Year")
			lq.setiTerm(180);
		else
			lq.setiTerm(360);
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		
		
		
		
		
			
		mainApp.messageSend(lq);
	}
	
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		double PaymentPossible = lRequest.getdIncome()*.28;
		double OtherPaymentPossible = (lRequest.getdIncome() - lRequest.getdExpenses())*.36;
		double FinalPaymentPossible;
		if(PaymentPossible < OtherPaymentPossible){
			FinalPaymentPossible = PaymentPossible;
		}
		else {
			FinalPaymentPossible = OtherPaymentPossible;
		}
	

		double payment = lRequest.getdPayment();
		String output;
		
		if(FinalPaymentPossible > payment){
			output = new DecimalFormat("#.##").format(payment);
			String APR = String.valueOf(lRequest.getdRate());
			lblError.setText("Mortgage payment will be: $" + output + " and your APR is " + APR + "%");
		}
		
		
		else{
			output = "It is Too High for the house cost.";
			lblError.setText(output);
		}

	}

	ObservableList<String> list = FXCollections.observableArrayList("15 Year","30 Year");
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loanTerm.setItems(list);	
	}
	
	
	
	
	public void HandleRateException(RateException e){
		lblError.setText("Too low for credit score on record");
	}
	
	
}