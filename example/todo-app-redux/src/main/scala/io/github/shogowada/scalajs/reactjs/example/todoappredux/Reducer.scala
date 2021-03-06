package io.github.shogowada.scalajs.reactjs.example.todoappredux

/*
* Reducer function has a signature of (Option[State], Action) => State.
* If the state is absent, return an initial state.
* */
object Reducer {
  def reduce(maybeState: Option[State], action: Any): State =
    State(
      todos = reduceTodos(maybeState.map(_.todos), action),
      visibilityFilter = reduceVisibilityFilter(maybeState.map(_.visibilityFilter), action)
    )

  private def reduceTodos(maybeTodos: Option[Seq[TodoItem]], action: Any): Seq[TodoItem] = {
    val todos = maybeTodos.getOrElse(Seq.empty)
    action match {
      case action: AddTodo => {
        todos :+ TodoItem(
          id = action.id,
          text = action.text,
          completed = false
        )
      }
      case action: ToggleTodo => {
        todos.map(todo => if (todo.id == action.id) {
          todo.copy(completed = !todo.completed)
        } else {
          todo
        })
      }
      case _ => todos
    }
  }

  private def reduceVisibilityFilter(maybeVisibilityFilter: Option[String], action: Any): String =
    action match {
      case action: SetVisibilityFilter => action.filter
      case _ => maybeVisibilityFilter.getOrElse(VisibilityFilters.ShowAll)
    }
}
