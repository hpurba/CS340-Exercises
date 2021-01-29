// 1. What design principles does this code violate?
//          This code breaks the simplicity rule.
// 2. Refactor the code to improve its design.

boolean greatScore = score > 700;
boolean greatIncome = income > 100000;
boolean moderateIncome = (income >= 40000) && (income <= 100000);
boolean moderateScore = score > 500;

if(greatScore){
    accept();
}
else if (moderateIncome && authorized && moderateScore){
    accept();
}
else if(){
    accept();
}
else {
    reject();
}

