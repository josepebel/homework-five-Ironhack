export class Trainer{

  constructor(
    private _name: string,
    private _age: number,
    private _hobby: string | undefined,
    private _picture: string
  ){

  }

  public get picture(): string {
    return this._picture;
  }
  public set picture(value: string) {
    this._picture = value;
  }
  public get hobby(): string | undefined {
    return this._hobby;
  }
  public set hobby(value: string | undefined) {
    this._hobby = value;
  }
  public get age(): number {
    return this._age;
  }
  public set age(value: number) {
    this._age = value;
  }
  public get name(): string {
    return this._name;
  }
  public set name(value: string) {
    this._name = value;
  }

}
