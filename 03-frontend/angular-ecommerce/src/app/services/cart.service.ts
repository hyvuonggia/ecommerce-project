import { Injectable } from '@angular/core';
import { CartItem } from '../common/cart-item';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  cartItems: CartItem[] = [];
  totalPrice: Subject<number> = new Subject<number>();
  totalQuantity: Subject<number> = new Subject<number>();

  constructor() {}

  addToCart(cartItem: CartItem) {
    // check if we already have item in the cart
    let alreadyExistsInCart: boolean = false;
    let existingCartItem: CartItem = undefined!;

    // find the item in the cart based on item id
    existingCartItem = this.cartItems.find(
      (tempCartItem) => tempCartItem.id === cartItem.id
    )!;

    // check if we found it
    alreadyExistsInCart = existingCartItem != undefined;

    if (alreadyExistsInCart) {
      existingCartItem.quantity++;
    } else {
      // add item to array
      this.cartItems.push(cartItem);
    }

    // compute cart total price and quantity
    this.computeCartTotals();
  }

  decrementQuantity(cartItem: CartItem) {
    cartItem.quantity--;

    if (cartItem.quantity === 0) {
      this.remove(cartItem);
    } else {
      this.computeCartTotals();
    }
  }
  
  remove(cartItem: CartItem) {
    // get index of item in the array
    const itemIndex = this.cartItems.findIndex(
      (tempCartItem) => tempCartItem.id === cartItem.id
    );

    // if found, remove the item from the array ath the given index
    if (itemIndex > -1) {
      this.cartItems.splice(itemIndex, 1);
      this.computeCartTotals();
    }
  }

  computeCartTotals() {
    let totalPriceValue: number = 0;
    let totalQuantityValue: number = 0;

    for (const currentCartItem of this.cartItems) {
      totalPriceValue += currentCartItem.quantity * currentCartItem.unitPrice;
      totalQuantityValue += currentCartItem.quantity;
    }

    // publish the new values, all subscribers will receive the new data
    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuantityValue);

    this.logCartData(totalPriceValue, totalQuantityValue);
  }

  logCartData(totalPriceValue: number, totalQuantityValue: number) {
    console.log(`Contents of the cart`);
    for (const item of this.cartItems) {
      const subTotalPrice = item.quantity * item.unitPrice;
      console.log(
        `name: ${item.name}, quantity=${item.quantity}, unitPrice=${item.unitPrice}, subTotalPrice=${subTotalPrice}`
      );
    }

    console.log(
      `totalPrice: ${totalPriceValue.toFixed(
        2
      )}, totalQuantity: ${totalQuantityValue}`
    );
    console.log('-------------------');
  }
}
