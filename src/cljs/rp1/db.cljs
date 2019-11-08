(ns rp1.db
  (:require [datascript.core :as datascript]
            [re-posh.core :as re-posh]))

;; the datascript api can be useful:
;; https://cljdoc.org/d/datascript/datascript/0.18.4/api/datascript.core

(def initial-db
  [{:db/id                        -1
    :app/type                     :type/create-todo-form
    :create-todo-form/title        ""
    :create-todo-form/description  ""}
   {:db/id            -2
    :app/type         :type/task
    :task/title       "Learn Clojure a little bit"
    :task/description "Just learn it"
    :task/done?       false}
   {:db/id            -3
    :app/type         :type/task
    :task/title       "Have a coffee"
    :task/description "Just relax"
    :task/done?       false}
   {:db/id            -4
    :app/type         :type/active-panel
    :active-panel/name :main}])

(def blank-db
  [{}])

(def conn (datascript/create-conn))
(re-posh/connect! conn)
