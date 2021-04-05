package newbank.server;

/**
 * Enum to define three types of deposit source.
 * Internal, a payment from another account of the bank
 * External, a payment from an account of another bank
 * Deposit, a payment made into an account, not from another account.
 */
public enum Source {

    Internal, External, Deposit
}
