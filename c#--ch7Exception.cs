using System;
using System.Linq;
using System.Text;
using System.Diagnostics;//where should you use this Debug.write()method?
using System.Collections.Generic;//what;s this for?

namespace csharf{
	class Textoutput{
		static void Main(string[] args){
			static int MaxSearch(int[] input, int[]index){
				Debug.WriteLine("Maximum value search started.");// which you ignore

				int count=0;
				int maxVal;
				maxVal=inpput[0];//the first number be the max
				index = new int[1];//default to initialize the index and foreach int var in index the value is 0
				index[0]=0;//here you'ra wrong,before this you must initialize the int[] --the syntax
				Debug.WriteLine(string.Format("Maximum value initialized to {0},at element index 0.",maxVal));//which you ignore

				for(int i=1;i</*lengthof(input)*/ input.Length;i++){// in C# using input.Length
					Debug.WriteLine(string.Format("Now looking at the element at index {0}.",i));//which you ignore

					if(input[i]>maxVal){
						maxVal=input[i];
						Consule.Write("now the maxValue of the inputarray is {0},the index is {1}",maxVal,i);// you can write with the method Debug.WriteLine(string.Format(""))
						count=1;//the bigest num ,used to be the result and at last will be output
						index[0]=i;

					}
					else {//here you can using{} to put the if part be a block
						if (input[i]==maxVal){
						Consule.Write("now the maxValue of the inputarray is {0},the index is {1}",maxVal,i);
						count++;
						//you should change the size of index
						int[] oldIndex = index;
						index = new int[count];
						oldIndex.CopyTo(index,0);//every time has the same maxVal you have to increase the size of index and Copy the index stored in the index
						index[count-1]=i;//if we have two maxVal the count willbe 2,and the i should be stored inthe index[1]

					}
				}
			}

				Consule.Write("at last,the maxVal is {0},and appearred {1} times",maxVal,count);
				//here you can write with the method Trace.WriteLine(string.Format("blablabla"))
				Debug.WriteLine("Maximum value search has been completed!");//which you ignored
				return maxVal;//GOD! you forgot this

			}
			int[] textarray={1,2,4,2,4,2,3,4,4,3,3};
			int[] index;
			int maxVal = MaxSearch(textarray,index);
			Console.WriteLine("Maximum value is {0} at element indices:",maxVal);
			foreach(int indexx in index){Console.WriteLine("indexx");}
			Console.ReadKey();//return ConsoleKeyInfo
		}

	}
}


//where should you change your code?--logic part is ok
//1 Trace.Write() and Debug.Write()
//2 index didnot deal with it well
//3 you dont know the out--keyword and the Console.readkey() and the foreach(int outindex in index)

//1.out 关键字会导致参数通过引用来传递。这与 ref 关键字类似，不同之处在于ref 要求变量必须在传递之前进行初始化。但是函数返回前必须赋值。
//2.方法定义和调用方法都必须显式使用 out 关键字。
//3.属性不是变量，因此不能作为 out 参数传递。
//4.如果希望方法返回多个值，可以声明 out 方法。使用 out 返回具有单个方法调用的三个变量。注意，第三个参数赋 null 值。这使得方法可以有选择地返回值。


//Debug.Write() Trace.Write() Console.Write() 语法相同
//Debug.WriteIf() Trace.WriteIf() Debug.WriteLineIf() Trace.WriteLineIf() 在条件满足情况下输出


//about ReadKey youcan learn all these methods about Key in MSDN
/*
using System;

class Example 
{
   public static void Main() 
   {
      ConsoleKeyInfo cki;
      // Prevent example from ending if CTL+C is pressed.
      Console.TreatControlCAsInput = true;

      Console.WriteLine("Press any combination of CTL, ALT, and SHIFT, and a console key.");
      Console.WriteLine("Press the Escape (Esc) key to quit: \n");
      do 
      {
         cki = Console.ReadKey();
         Console.Write(" --- You pressed ");
         if((cki.Modifiers & ConsoleModifiers.Alt) != 0) Console.Write("ALT+");
         if((cki.Modifiers & ConsoleModifiers.Shift) != 0) Console.Write("SHIFT+");
         if((cki.Modifiers & ConsoleModifiers.Control) != 0) Console.Write("CTL+");
         Console.WriteLine(cki.Key.ToString());
       } while (cki.Key != ConsoleKey.Escape);
    }
}*/

//ConsoleKeyInfo.Modifiers TreatControlCAsInput ConsoleModifiers.Alt ConsoleKeyInfo.Key.ToString() ConsoleKey.Escape


//using the try...catch...finally --if something wrong in the try block,wewill find whether thereis a catch to match the exception.
//and the finally wewill execute no matter we execute the catch or not .this part we have to execute as long as it exists.









