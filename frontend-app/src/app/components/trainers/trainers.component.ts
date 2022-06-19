import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TrainerService } from 'src/app/services/trainer.service';
import { Trainer } from '../../models/trainer.model';

@Component({
  selector: 'app-trainers',
  templateUrl: './trainers.component.html',
  styleUrls: ['./trainers.component.css']
})
export class TrainersComponent implements OnInit {

  trainerList: Trainer[]
  hobbiesList: string[]
  pictureList: string[]
  isTrainerListEmpty: boolean;

  registerForm: FormGroup;
  nameInput: FormControl;
  ageInput: FormControl;
  hobbyInput: FormControl;
  pictureInput: FormControl;

  hobbySelected: boolean;

  constructor(private trainerService: TrainerService) {

    this.trainerList = [];
    this.isTrainerListEmpty = true;

    this.hobbiesList = ["Bugcatcher", "Blackbelt", "Picnicker", "Champion", "Hiker", "Fisherman", "Scientist", "Swimmer", "Pokemaniac", "Cooltrainer"];
    this.pictureList = ["https://cdn2.bulbagarden.net/upload/5/55/Red_Blue_Bug_Catcher.png", "https://cdn2.bulbagarden.net/upload/8/81/XY_Black_Belt.png", "https://cdn2.bulbagarden.net/upload/2/22/ORAS_Picnicker.png",
      "https://cdn2.bulbagarden.net/upload/thumb/8/83/FireRed_LeafGreen_Red.png/278px-FireRed_LeafGreen_Red.png", "https://cdn2.bulbagarden.net/upload/6/6b/Ruby_Sapphire_Hiker.png", "https://cdn2.bulbagarden.net/upload/f/fc/ORAS_Fisherman.png",
      "https://cdn2.bulbagarden.net/upload/d/d0/XY_Scientist_F.png", "https://cdn2.bulbagarden.net/upload/3/39/XY_Swimmer_M.png", "https://cdn2.bulbagarden.net/upload/3/32/ORAS_Pok%C3%A9_Maniac.png",
      "https://cdn2.bulbagarden.net/upload/6/6f/XY_Ace_Trainer_F.png"];

    const reg = "((http|https)://)(www.)?"
      + "[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]"
      + "{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)";

    this.nameInput = new FormControl("", [Validators.required]);
    this.ageInput = new FormControl("", [Validators.required, Validators.min(1), Validators.max(99)]);
    this.hobbyInput = new FormControl("");
    this.pictureInput = new FormControl("", [Validators.required, Validators.pattern(reg)]);

    this.registerForm = new FormGroup({
      name: this.nameInput,
      age: this.ageInput,
      hobby: this.hobbyInput,
      picture: this.pictureInput
    })

    this.hobbySelected = false;
  }

  ngOnInit(): void {

   this.getTrainers();
  }

  onSubmit(): void {

    const name: string = this.registerForm.get('name')?.value;

    let repeatName: boolean = false;
    for (let i: number = 0; i < this.trainerList.length; i++) {

      if (name === this.trainerList[i].name) {
        repeatName = true;
      }
    }

    if (!repeatName) {
      const age: number = this.registerForm.get('age')?.value;
      let hobby: string | undefined = this.registerForm.get('hobby')?.value;
      if (hobby?.length === 0) {
        hobby = undefined;
      }
      const picture: string = this.registerForm.get('picture')?.value;
      const trainer = new Trainer(name, age, hobby, picture);

      this.trainerService.addTrainer(trainer).subscribe({
        next: dataResult => {
          this.registerForm.reset();
          this.getTrainers();
        }
        ,
        error: error => {
          console.error("There was an error!", error);
        }
      })
    } else {
      alert("Please, enter a non-repeated name");
    }
  }

  selectPicture(): void {

    for (let index = 0; index < this.hobbiesList.length; index++) {
      if (this.hobbyInput.value == this.hobbiesList[index])
        this.pictureInput.setValue(this.pictureList[index]);
    }

    if (this.registerForm.value.hobby !== "") {
      this.hobbySelected = true;
    } else {
      this.hobbySelected = false;
      this.registerForm.value.hobby = "Custom";
    }
  }

  deleteTrainer(name: string, index: number): void {

    this.trainerService.deleteTrainer(name).subscribe({
      next: dataResult => {
        this.trainerList.splice(index, 1);
        if (this.trainerList.length == 0) {
          this.isTrainerListEmpty = true;
        } else {
          this.isTrainerListEmpty = false;
        }
      }
      ,
      error: error => {
        console.error("Ther was an error!", error);
      }
    })
  }

  getTrainers(){
    this.trainerService.getTrainers().subscribe({
    next: dataResult => {
      this.trainerList = dataResult;
      if (this.trainerList.length == 0) {
        this.isTrainerListEmpty = true;
      } else {
        this.isTrainerListEmpty = false;
      }
    }
    ,
    error: error => {
      console.error("Ther was an error!", error);
    }
  })
  }
}
