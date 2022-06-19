import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Trainer } from '../../models/trainer.model';

@Component({
  selector: 'app-trainer-item',
  templateUrl: './trainer-item.component.html',
  styleUrls: ['./trainer-item.component.css']
})
export class TrainerItemComponent implements OnInit {

  @Input()
  trainer!: Trainer;

  @Output()
  deleteTrainerEvent: EventEmitter<void> = new EventEmitter;

  constructor() {

   }

  ngOnInit(): void {
  }

  deleteTrainer(): void{
    this.deleteTrainerEvent.emit()
  }

}
