class Solution {
    public int bulbSwitch(int n) {
        // n bulbs and n rounds
        // a specific bulb i will switch only in the round of its factors
        // i.e. bulb 12 will switch only in round 1, 2, 3, 4, 6 and 12
        // 
        // also, it is after even switch operations the bulbs turn back to off (off->on->off)
        // if the bulb i has odd factors, then it will have odd switch operations ending with
        // an ON state (off->on->off->on)
        // and only square numbers have odd factors (36 : 1, 2, 4, 9, 18, '6')
        // 
        // the question turns to: finding the number of square numbers in n
        // sqrt(n) is the maximum square numbers, so there are 1---sqrt(n) square 
        // numbers smaller than n, therefore just return (int) Math.sqrt(n)
        return (int) Math.sqrt(n);
    }
}
