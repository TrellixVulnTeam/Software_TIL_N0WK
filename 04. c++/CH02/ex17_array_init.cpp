#include <iostream>
using namespace std;

int main() {
  int scores[] = {
    100,200,300,400,503
  };
 const int STUDENTS = sizeof(scores)/sizeof(int);

 int sum = 0;
 int i, average;

 for (i=0; i<STUDENTS; i++){
   sum += scores[i];
 }
 average = (double)sum / STUDENTS; // () : ĳ���� ������
 cout << "���� ��� = " << average << endl;

 return 0;
}