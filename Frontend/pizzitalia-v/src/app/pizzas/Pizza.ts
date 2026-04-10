import { CoupanModel } from "./pizza-details/Coupan";


export class PizzaModel{
 
  constructor(
  public id : number,
  public name: string,
  public quantity: number,
  public type: string,
  public description: string,
  public price: number,
  public url: string,
  public size:string,
  public coupan:CoupanModel[],
 

  ){}
       
}


      