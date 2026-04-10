import { PizzaModel } from "app/pizzas/Pizza";
import { CoupanModel } from "../coupan/Coupan";


export class PizzaOrderModel{
    public bookingOrderId!: number;
	public orderDate!: Date;
	public transactionMode!: string;
	public quantity!: number;
	public size:string="medium";
	public totalCost!:number;
 	public pizza!: PizzaModel;
	public coupan!:CoupanModel;
	constructor(p:PizzaOrderModel){
		this.bookingOrderId=p.bookingOrderId
		this.coupan=p.coupan
		this.orderDate=p.orderDate
		this.pizza=p.pizza
		this.quantity=p.quantity
		this.size=p.size
		this.totalCost=p.totalCost
		this.transactionMode=p.transactionMode
	}
 
}
export class PizzaOrderDtoModel{
	public bookingOrderId!: number;
	public orderDate!: Date;
	public transactionMode!: string;
	public quantity!: number;
	public size:string="medium";
	public totalCost!:number;
 	public pizza!: number;
	public coupan!:number;
	
	constructor(object:PizzaOrderModel|PizzaModel){
		if(object instanceof PizzaOrderModel){
		this.bookingOrderId=object.bookingOrderId
		this.orderDate=object.orderDate
		this.quantity=object.quantity
		this.size=object.size
		this.totalCost=object.totalCost
		this.transactionMode=object.transactionMode
		this.pizza=object.pizza.id
		this.coupan=1
	}
	else{
		this.pizza=object.id
		this.quantity=object.quantity
		this.transactionMode="Online"
		this.coupan=1
		if(object.size===undefined){
			this.size="medium"
		}
		else{
		this.size=object.size
		}
	}
	
}
}