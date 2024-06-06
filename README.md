<h1 align="center">Лабораторна 3-a</h1>
 <h2 align="center">1. Floyd-Warshall algorithm</h2>
      <p>За результатами виконання тестів, я отримала наступні часи виконання для послідовного і паралельного алгоритмів
        Флойда-Уоршелла:</p>
      <h3>Для графа з 500 вузлами:</h3>
      Послідовний: 125 мілісекунд<br>
      Паралельний: 248 мілісекунд
      <br>
      <h3>Для графа з 10 вузлами:</h3>
      Послідовний: 1 мілісекунда<br>
      Паралельний: 3 мілісекунди<br>
     <p> Отже, доцільніше використовувати послідовний алгоритм, тоді як для великих графів необхідно
      оптимізувати паралельний алгоритм або залишити послідовний підхід для кращої продуктивності.
      </p>
      <h2 align="center">2. BFS algorithm</h2>
      <p>За результатами виконання тестів, я отримала наступні часи виконання для послідовного і паралельного BFS:
        <br>
        Послідовний BFS: 0 мілісекунд<br>
        Паралельний BFS: 0 мілісекунд
        <br>
        Граф, використаний у тестах, має 6 вузлів і наступні ребра: (0-1), (0-2), (1-3), (1-4), (2-4), (3-4), (3-5),
        (4-5).За результатами виконання тестів можна зробити висновок, що обидва алгоритми виконуються миттєво.
      <h2 align="center">3.Dijkstra algorithm </h2>
        <p>За результатами виконання тестів, я отримала наступні часи виконання для послідовного і паралельного алгоритмів Дейкстри:
      <h3>Для графа з 100 вузлами:</h3>
      Послідовний: 0 мілісекунд<br>
      Паралельний: 10 мілісекунди
      <h3>Для графа з 10000 вузлами:</h3>
      Послідовний: 285 мілісекунд<br>
      Паралельний: 1385 мілісекунд<br>
      На основі наведених результатів можна зробити висновок, що паралельний алгоритм Дейкстри не завжди забезпечує
      прискорення порівняно з послідовним.
      Для графів з невеликою кількістю вузлів (100 вузлів) паралельний алгоритм виконується повільніше, ніж послідовний.
      Це може бути пов'язано з накладними витратами на створення та управління потоками. Для графів з великою кількістю
      вузлів (10000 вузлів) паралельний алгоритм також показує гірший час виконання.
      </p>
      <h2 align="center">4.Mergesort algorithm </h2>
 <p>За результатами виконання тестів, я отримала наступні часи виконання для послідовного і паралельного алгоритмів сортування злиттям:
        <h3>Для масива з 100 елементів:</h3>
        Послідовний: 0 мілісекунд<br>
        Паралельний: 5 мілісекунд
        <br>
        <h3>Для масива з 100000 елементів:</h3>
        Послідовний: 57 мілісекунд<br>
        Паралельний: 47 мілісекунд
        <br>
        Отже,паралельний алгоритм сортування злиттям демонструє кращу продуктивність на масиві з 100000 елементів порівняно з послідовним алгоритмом.
        Однак, на масиві з 100 елементів краще використовувати послідовний алгоритм, ніж паралельний, через додаткові витрати на створення та управління потоками.
      </p>
      <h2 align="center">5.Quicksort algorithm </h2>
  <p>За результатами виконання тестів, я отримала наступні часи виконання для послідовного і паралельного алгоритмів швидкого сортування:
  <h3>Для масива з 100 елементів:</h3>
   Послідовний: 0 мілісекунд<br>
   Паралельний: 0 мілісекунд
      <br>
      <h3>Для масива з 100000 елементів:</h3>
      Послідовний: 14 мілісекунд<br>
      Паралельний: 6 мілісекунд
      <br>
      Отже,результати тестування показують, що для невеликих масивів (100 елементів) обидва алгоритми працюють практично
      миттєво, без помітних відмінностей у часі виконання.
      Однак, для більших масивів (100000 елементів) паралельний алгоритм QuickSort показує значно кращий результат у
      швидкості виконання порівняно з послідовним алгоритмом.
      </p>
    