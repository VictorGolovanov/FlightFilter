# Test task
#### FlightFilter
Для проверочного запуска создайте публичный класс Main c методом main() Этот метод должен выдать в консоль результаты обработки тестового набора перелётов. Получить тестовый набор нужно методом FlightBuilder.createFlights()
Поместить в main() проверочный код. Исключите из тестового набора перелёты по следующим правилам (по каждому правилу нужен отдельный вывод списка перелётов):
1. вылет до текущего момента времени
2. имеются сегменты с датой прилёта раньше даты вылета
3. общее время, проведённое на земле превышает два часа (время на земле — это интервал между прилётом одного сегмента и вылетом следующего за ним)

На каждый случай подготовлен отдельный метод в классе FlightFilter. 
Эти методы получают на вход список всех полетов, а на выход дают новый список из которого удалены соответствующие перелеты.
