
### Используемые технологии

- В данном проекте была применена clean архитектура с разбиением по слоям (app-data-domain). 
- Presentation слой построен по принципу MVI с использованием viewModel. Преимущество перед MVVM - отсутствие рисков неконсистентных состояний. Для передачи данных стейта из viewModel использовался StateFlow. 
- Для получения данных из интернета исползовался retrofit, работа которого осуществляется через и репозиторий.
- бизнес логика вынесена в usecase
- Для кэширования данных на устройстве использовалась библиотека Room. Ui подписывается на flow базы данных, при загрузке новых данных из интернета, данные в бд заменяются на актуальные
- Навигация между экранами осуществляется при помощи navigation component 
- стили прописывались через styles.xml, а строки через strings.xml - что позволяет проще локализовать приложение под другой язык
- Для DI использовался Dagger 2 с применением Dependent компонентов, и скоупов для UI
- Для проверки интернет соединения используется ConnectivityManager



### Комментарии к выполнению

Реализовано:
1. При входе в мобильное приложение должен отобразиться экран со списком
курса валют относительно рубля на текущую дату.
2. При вводе названия валюты должна выполниться фильтрация списка по
введенному значению.
3. При нажатии на карточку с курсом валюты, должен выполниться переход на
второй экран с конвертацией рублей в выбранную валюту.
4. При вводе количества рублей мобильное приложение конвертирует введенную
сумму в выбранную валюту и отображает результат.

помимо этого:
- во время загрузки крутится progress bars, также реализован swipe-to-refresh 
- реализовано кэширование загруженных данных
- при ошибки загрузки данных или отсутствии интернета отображается сообщение об этом
 

### Демонстрация работы приложения 

Видеоролик, демонстрирующий работу приложения: https://disk.yandex.ru/i/0GmE3tCLVQcKuQ

