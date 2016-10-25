export default class {
  constructor(values) {
    this.id = null;
    this.firstName = null;
    this.lastName = null;
    this.email = null;
    this.telephone = null;
    // this.licensePlate = null;
    // this.dmsId = null;
    // this.model = null;
    // this.lastMileage = null;

    _.merge(this, values);
  }
}