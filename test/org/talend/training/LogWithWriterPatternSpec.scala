package org.talend.training

import org.specs2.mutable.Specification

class LogWithWriterPatternSpec extends Specification {

  "LogWithWriterPattern" should {
    "compose logs" in {
      val result = LogWithWriterPattern.action(5)

      result.value === "5.0 from baz"
      result.log ===
        """compute double by foo
          |stringify by bar
          |decorated by baz""".stripMargin
    }
  }

}
