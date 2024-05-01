package com.hyvuonggia.ecommerce.service;

import com.hyvuonggia.ecommerce.dto.Purchase;
import com.hyvuonggia.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

	PurchaseResponse placeOrder(Purchase purchase);
}
