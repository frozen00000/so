import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {ItemPage} from "../models/itemPage.model";
import {Observable} from "rxjs/internal/Observable";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root',
})
export class QuestionService {

  constructor(private http: HttpClient) {}

  private url = environment.apiHost + '/api/search';

  public fetchQuestions(query: string, page: number, pageSize: number): Observable<ItemPage> {
    return this.http.get<ItemPage>(
      this.url,
      {
        headers: {
          'Content-Type': 'application/json'
        },
        params: {query: query, page: page.toString(), pageSize: pageSize.toString()}
      });
  }

}
