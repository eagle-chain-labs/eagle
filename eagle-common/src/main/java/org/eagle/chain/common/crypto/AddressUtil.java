package org.eagle.chain.common.crypto;


import org.bitcoinj.core.AddressFormatException;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.eagle.chain.common.base.EnvInstance;
import org.eagle.chain.common.crypto.encode.Bech32;
import org.eagle.chain.common.crypto.encode.ConvertBits;
import org.web3j.crypto.Keys;
import org.web3j.utils.Numeric;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.bitcoinj.core.ECKey.CURVE;

public class AddressUtil {

    public static String createNewAddressSecp256k1(String mainPrefix, byte[] publickKey) throws Exception {
        // convert 33 bytes public key to 65 bytes public key
        byte[] uncompressedPubKey = CURVE.getCurve().decodePoint(publickKey).getEncoded(false);
        byte[] pub = new byte[64];
        // truncate last 64 bytes to generate address
        System.arraycopy(uncompressedPubKey, 1, pub, 0, 64);

        //get address
        byte[] address = Keys.getAddress(pub);

        //get qiyichain
        String addressResult = null;
        try {
            byte[] bytes = encode(0, address);
            addressResult = Bech32.encode(mainPrefix, bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return addressResult;
    }


    public static String getPubkeyBech32FromValue(String mainPrefix, byte[] publickKeyValue) throws Exception {
        try {
            byte[] bytes = encode(0, publickKeyValue);
            String pubBech32 = Bech32.encode(mainPrefix, bytes);
            return pubBech32;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static byte[] decodeAddress(String address){
        byte[] dec = Bech32.decode(address).getData();
        return ConvertBits.convertBits(dec, 0, dec.length, 5, 8, false);
    }

    private static byte[] sha256Hash(byte[] input, int offset, int length) throws NoSuchAlgorithmException {
        byte[] result = new byte[32];
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(input, offset, length);
        return digest.digest();
    }

    private static byte[] encode(int witnessVersion, byte[] witnessProgram) throws AddressFormatException {
        byte[] convertedProgram = ConvertBits.convertBits(witnessProgram, 0, witnessProgram.length, 8, 5, true);
        return convertedProgram;
    }

    public static String convertAddressFromHexToBech32(String hexAddress){
        byte[] address = Numeric.hexStringToByteArray(hexAddress);

        String bech32Address = null;
        try {
            byte[] bytes = encode(0, address);
            bech32Address = Bech32.encode(EnvInstance.getEnv().getMainPrefix(), bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bech32Address;
    }

    public static String convertAddressFromBech32ToHex(String bech32Address){
        String hexAddress = null;
        try {
            byte[] bytes = decodeAddress(bech32Address);
            hexAddress = Numeric.toHexString(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Keys.toChecksumAddress(hexAddress);
    }

    public static String convertAddressFromBech32ToVal(String bech32Address){
        String valAddress = null;
        try {
            byte[] bytes = Bech32.decode(bech32Address).getData();
            valAddress = Bech32.encode(EnvInstance.getEnv().getValidatorAddrPrefix(), bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return valAddress;
    }

    public static void main(String[] args) {
        NetworkParameters params = MainNetParams.get();
        ECKey key = new ECKey();
        System.out.format("私钥 => %s\n", key.getPrivateKeyAsHex());
        System.out.format("公钥 => %s\n", key.getPublicKeyAsHex());
        System.out.format("地址 => %s\n", key.toAddress(params));
    }

}
