import {Component, Input} from '@angular/core';
import {QuestionService} from "./services/question.service";
import {ItemPage} from "./models/itemPage.model";
import {ItemModel} from "./models/item.model";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Application for searching in Stackoverflow';
  questions: ItemModel[];
  hasMore: boolean = false;
  @Input() query: string = '';
  lastQuery: string;
  page: number = 1;
  pageSize: number = 20;

  constructor(private itemService: QuestionService) {}

  fetchQuestions(query: string, consumer: (value: ItemPage) => void) {
    this.itemService.fetchQuestions(query, this.page, this.pageSize)
      .subscribe(consumer, error => alert(error.message));
  }

  search() {
    if (this.query) {
      this.questions = [];
      this.hasMore = false;
      this.page = 1;
      this.fetchQuestions(this.query, data => {
        this.questions = data.items;
        this.hasMore = data.hasMore;
        this.lastQuery = this.query;
      });
    } else {
      alert('Query should not be empty.')
    }
  }

  showMore() {
    if (this.lastQuery && this.hasMore) {
      this.page++;
      this.fetchQuestions(this.lastQuery, data => {
        this.questions = this.questions.concat(data.items);
        this.hasMore = data.hasMore;
      });
    } else {
      console.warn("Invalid state.")
    }
  }

}
