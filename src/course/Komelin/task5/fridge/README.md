Подсказка как заполнять объект Fridge.
Начальные характеристики смотри в README в пакете Appliance.

1. Указать, есть ли морозильная камера **setHasFreezer(boolean)** - по умолчанию false
2. Если указал, что есть морозильная камера, укажи ее объем **setFreezerVolume(double)** - по умолчанию 0
3. Сколько камер в холодильнике **setCamerasCount(int)** - по умолчанию 2
4. Укажи тип холодильника из enum FridgeTypes (находится в fridge.types) **setFridgeType(FridgeTypes)** - по умолчанию FridgeTypes.FRIDGE
5. Объект готов к использованию :3

Если ты хочешь создать объект импортного холодильника, то создавай объект класса ImportedFridge.

1. Выполни пункты 1-4 для Fridge
2. Укажи страну изготовителя **setCountryManufacturer(String)**
3. Укажи имеется ли гарантия **setHasWarranty(boolean)**
4. Объект готов к использованию :3