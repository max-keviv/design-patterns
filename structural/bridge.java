import java.util.*;

// suppose we have payment orchestration and different payment gateways 
// Juspay Stripe hyperswitch
// and now we have different payment methods
// cards upi vpa netbanking 

// so without bridge design pattern we have to declare every payment orchestration to payment methods

// juspayCards juspayupi juspayvpa juspaynetbanking stripeCards stripeUpi stripeVpa stripeNetbanking ... ... .... 

// but the problem is you have to declare the class for each relation between payment orchestration this is not scalable as it grow expontential and it become problematic to change the product

// to solve this we will use bridge design pattern for this we will decouple the abstraction from implementation 
// implementation is the low level interface which does the actual work like in this case payment orchestration 
// abstraction will be the code which will interact with the client like payment methods 

interface PaymentOrchestration {
    public void processPayment();
    public void refund();
    public void status();
}

class Juspay implements PaymentOrchestration {
// Juspay implementation
    @Override
    public void processPayment(){
        System.out.println("Juspay has processed the payment");
    }
    @Override
    public void refund(){
        System.out.println("Juspay has refunded the payment");
    }
    @Override
    public void status(){
        System.out.println("Juspay payment status");
    }
}

class Stripe implements PaymentOrchestration {
    @Override
    public void processPayment(){
        System.out.println("Stripe has processed the payment");
    }
    @Override
    public void refund(){
        System.out.println("Stripe has refunded the payment");
    }
    @Override
    public void status(){
        System.out.println("Stripe payment status");
    }
    
}

class Razorpay implements PaymentOrchestration {
    @Override
    public void processPayment(){
        System.out.println("Razorpay has processed the payment");
    }
    @Override
    public void refund(){
        System.out.println("Razorpay has refunded the payment");
    }
    @Override
    public void status(){
        System.out.println("Razorpay payment status");
    }
}

abstract class PaymentMethod {
    public PaymentOrchestration paymentOrchestration;
    public PaymentMethod(PaymentOrchestration paymentOrchestration){
        this.paymentOrchestration = paymentOrchestration;
    } 

    public abstract void makePayment();
    public abstract void refund();
    public abstract void checkStatus();
}

class Cards extends PaymentMethod {
    public Cards(PaymentOrchestration paymentOrchestration) {
            super(paymentOrchestration);
        }
    @Override
    public void makePayment() {
        System.out.println("Processing UPI payment...");
        paymentOrchestration.processPayment();
    }

    @Override
    public void refund() {
        System.out.println("Refunding UPI payment...");
        paymentOrchestration.refund();
    }

    @Override
    public void checkStatus() {
        System.out.println("Checking UPI payment status...");
        paymentOrchestration.status();
    }
}


class UPI extends PaymentMethod {
    public UPI(PaymentOrchestration paymentOrchestration) {
            super(paymentOrchestration);
        }
    @Override
    public void makePayment() {
        System.out.println("Processing UPI payment...");
        paymentOrchestration.processPayment();
    }

    @Override
    public void refund() {
        System.out.println("Refunding UPI payment...");
        paymentOrchestration.refund();
    }

    @Override
    public void checkStatus() {
        System.out.println("Checking UPI payment status...");
        paymentOrchestration.status();
    }
}



public class bridge {
 public static void main(String[] args)
 {
    PaymentOrchestration juspay = new Juspay();
        PaymentMethod juspayUPI = new UPI(juspay);
        juspayUPI.makePayment();
        juspayUPI.checkStatus();

        // Razorpay Cards Payment
        PaymentOrchestration razorpay = new Razorpay();
        PaymentMethod razorpayCard = new Cards(razorpay);
        razorpayCard.makePayment();
        razorpayCard.refund();

        // Stripe UPI Payment
        PaymentOrchestration stripe = new Stripe();
        PaymentMethod stripeUPI = new UPI(stripe);
        stripeUPI.makePayment();
 }
}