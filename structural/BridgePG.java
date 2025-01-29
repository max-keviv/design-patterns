interface PaymentGateway {
    void processPayment(String method);
    void refund(String method);
    void status(String method);
}

class Juspay implements PaymentGateway {
    @Override
    public void processPayment(String method) {
        System.out.println("Juspay processing " + method + " payment");
    }
    @Override
    public void refund(String method) {
        System.out.println("Juspay refunded " + method + " payment");
    }
    @Override
    public void status(String method) {
        System.out.println("Juspay checking " + method + " payment status");
    }
}

class Stripe implements PaymentGateway {
    @Override
    public void processPayment(String method) {
        System.out.println("Stripe processing " + method + " payment");
    }
    @Override
    public void refund(String method) {
        System.out.println("Stripe refunded " + method + " payment");
    }
    @Override
    public void status(String method) {
        System.out.println("Stripe checking " + method + " payment status");
    }
}

class Razorpay implements PaymentGateway {
    @Override
    public void processPayment(String method) {
        System.out.println("Razorpay processing " + method + " payment");
    }
    @Override
    public void refund(String method) {
        System.out.println("Razorpay refunded " + method + " payment");
    }
    @Override
    public void status(String method) {
        System.out.println("Razorpay checking " + method + " payment status");
    }
}

abstract class PaymentOrchestration {
    public PaymentGateway paymentGateway;

    public PaymentOrchestration(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public abstract void makePayment();
    public abstract void refund();
    public abstract void checkStatus();
}

class UPI extends PaymentOrchestration {
    public UPI(PaymentGateway paymentGateway) {
        super(paymentGateway);
    }

    @Override
    public void makePayment() {
        paymentGateway.processPayment("UPI");
    }

    @Override
    public void refund() {
        paymentGateway.refund("UPI");
    }

    @Override
    public void checkStatus() {
        paymentGateway.status("UPI");
    }
}

class Cards extends PaymentOrchestration {
    public Cards(PaymentGateway paymentGateway) {
        super(paymentGateway);
    }

    @Override
    public void makePayment() {
        paymentGateway.processPayment("Cards");
    }

    @Override
    public void refund() {
        paymentGateway.refund("Cards");
    }

    @Override
    public void checkStatus() {
        paymentGateway.status("Cards");
    }
}

public class BridgePG {
    public static void main(String[] args) {
        System.out.println("Bridge design pattern for PG");
        // Juspay UPI Payment
        PaymentGateway juspay = new Juspay();
        PaymentOrchestration juspayUPI = new UPI(juspay);
        juspayUPI.makePayment();
        juspayUPI.checkStatus();

        // Razorpay Cards Payment
        PaymentGateway razorpay = new Razorpay();
        PaymentOrchestration razorpayCard = new Cards(razorpay);
        razorpayCard.makePayment();
        razorpayCard.refund();

        // Stripe UPI Payment
        PaymentGateway stripe = new Stripe();
        PaymentOrchestration stripeUPI = new UPI(stripe);
        stripeUPI.makePayment();
    }
}