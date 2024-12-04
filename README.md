prompt 1:
As a Parking Manager, I am responsible for managing three parking lots: ● The Plaza Park (9 parking capacity) ● City Mall Garage (12 parking capacity) ● Office Tower Parking (9 parking capacity) I have employed three Parking Boys to help manage these parking lots, each utilizing a specific parking strategy:  
Standard parking strategy
Smart parking strategy
Super Smart parking strategy
implement A parking manager class, with the above 3 parking lots and 3 parking boys, manager have the following 3 method: getAllParkingLots(): return all parking lots park(plate number, strategy): request the correct parking boy to do the parking job and return a ticket fetch(plate number): fetch the car from the corresponding parking lot and return the car.

prompt 2:
generate unit tests for the parking manager class with the given when then format

prompt 3:
refactor the below 3 tests into 1 test with parameterizedTest

prompt 4:
// Get /allParkingLots
// response: List<ParkingLot>
base on the above API, implement the getAllParkingLots() method in the ParkingManagerController class

prompt 5:
now I have a get all parking lots API, I want to have a integration test for this API, please implement the test using MockMvc.

prompt 6:
// Post /park
// request: String plateNumber, int strategy
// response: Ticket
base on the above API, implement the park() method in the ParkingManagerController class

prompt 7:
now I have a park API, I want to have a integration test for this API, please implement the test using MockMvc.

prompt 8:
// Post /fetch
// request: String plateNumber
// response: Car
base on the above API, implement the fetch() method in the ParkingManagerController class

prompt 9:
now I have a fetch API, I want to have a integration test for this API, please implement the test using MockMvc.