import {Component, Input} from '@angular/core';
import {ItemModel} from "../models/item.model";

@Component({
  selector: 'app-item',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent {

  @Input() itemModel: ItemModel;

  get answerBadgeStyle() {
    return "badge " + (this.itemModel.isAnswered ? "badge-success" : "badge-secondary");
  }

  get creationDate() {
    return new Date(this.itemModel.creationDate * 1000).toUTCString();
  }

}
