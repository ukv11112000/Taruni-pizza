package com.example.demo.exception;


@SuppressWarnings("serial")
public class PizzaOrderInvalidException extends RuntimeException {


		private final  String message;
		private final  String details;
		public String getMessage() {
			return message;
		}
		
		public String getDetails() {
			return details;
		}

		public PizzaOrderInvalidException() {
			super();
			this.message = "Invalid Pizza Order";
			this.details = "None";
		}
		

		public PizzaOrderInvalidException( String details) {
			super();
			this.message = "Invalid Pizza Order";
			this.details = details;
		}
		
	

}