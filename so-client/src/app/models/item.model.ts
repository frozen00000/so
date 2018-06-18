import {Owner} from "./owner.model";

export class ItemModel {

  title: string;
  owner: Owner;
  tags: string[];
  isAnswered: boolean;
  answerCount: number;
  link: string;
  score: number;
  creationDate: number;

}
