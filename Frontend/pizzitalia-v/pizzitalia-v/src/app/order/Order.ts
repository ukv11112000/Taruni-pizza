import { PizzaOrderModel } from "app/pizza-order/PizzaOrder";

export class OrderModel{
	orderid!:number;
	orderType!:string;
	orderDescription!:string;
	orderCustomerId!:number;
	success!:boolean;
	pizzaorder:PizzaOrderModel[]=[];
	orderTotal!:number;
	constructor(
		orderType:string,
		orderDescription:string,
		orderCustomerId:number,
		success:boolean,
		orderTotal:number){
			this.orderType=orderType
			this.orderDescription=orderDescription
			this.orderCustomerId=orderCustomerId
			this.success=success
			this.orderTotal=orderTotal

		}
}
export class OrderDtoModel{
	orderid!:number;
	orderType!:string;
	orderDescription!:string;
	orderCustomerId!:number;
	success!:boolean;
	pizzaorder:number[]=[];
	orderTotal!:number;
	constructor(
		orderType:string,
		orderDescription:string,
		orderCustomerId:number,
		success:boolean,
		orderTotal:number){
			this.orderType=orderType
			this.orderDescription=orderDescription
			this.orderCustomerId=orderCustomerId
			this.success=success
			this.orderTotal=orderTotal
			
		}
}
