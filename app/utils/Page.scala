package utils

case class Page[T](result: Seq[T], page: Int, pageSize: Int, total: Int)
